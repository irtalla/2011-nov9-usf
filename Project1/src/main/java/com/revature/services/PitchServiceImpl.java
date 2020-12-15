package com.revature.services;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.revature.app.SpmsAppJavalin;
import com.revature.data.AdditionalFileDAO;
import com.revature.data.AdditionalFileDAOFactory;
import com.revature.data.GenreDAO;
import com.revature.data.GenreDAOFactory;
import com.revature.data.PitchDAO;
import com.revature.data.PitchDAOFactory;
import com.revature.data.PitchStageDAO;
import com.revature.data.PitchStageDAOFactory;
import com.revature.data.RequestDAOFactory;
import com.revature.data.ReviewStatusDAO;
import com.revature.data.ReviewStatusDAOFactory;
import com.revature.data.StoryTypeDAO;
import com.revature.data.StoryTypeDAOFactory;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;
import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.models.User;
import com.revature.models.AdditionalFile;

public class PitchServiceImpl implements PitchService {
	private PitchDAO pitchDao;
	private GenreDAO genreDao;
	private StoryTypeDAO stDao;
	private PitchStageDAO psDao;
	private ReviewStatusDAO rsDao;
	private UserDAO uDao;
	private AdditionalFileDAO afDao;
	
	public PitchServiceImpl() {
		PitchDAOFactory pitchFactory = new PitchDAOFactory();
		pitchDao = pitchFactory.getPitchDao();
		GenreDAOFactory genreFactory = new GenreDAOFactory();
		genreDao = genreFactory.getGenreDao();
		StoryTypeDAOFactory stFactory = new StoryTypeDAOFactory();
		stDao = stFactory.getStoryTypeDao();
		PitchStageDAOFactory psFactory = new PitchStageDAOFactory();
		psDao = psFactory.getPitchStageDao();
		ReviewStatusDAOFactory rsFactory = new ReviewStatusDAOFactory();
		rsDao = rsFactory.getReviewStatusDao();
		UserDAOFactory uFactory = new UserDAOFactory();
		uDao = uFactory.getUserDao();
		AdditionalFileDAOFactory afFactory = new AdditionalFileDAOFactory();
		afDao = afFactory.getAdditionalFileDao();
	}
	
	@Override
	public Integer addPitch(Pitch t) throws Exception {
		return pitchDao.add(t);
	}

	@Override
	public Pitch getPitchById(Integer id) {
		return pitchDao.getById(id);
	}

	@Override
	public Set<Pitch> getPitchesByAuthor(Integer id) {
		User author = uDao.getById(id);
		return pitchDao.getByAuthor(author);
	}

	@Override
	public Set<Pitch> getPitchesByGenre(Boolean withinGenre, Integer ... genreIds) {
		List<Genre> genres = new ArrayList<>();
		for (Integer id : genreIds) {
			genres.add(genreDao.getById(id));
		}
		return pitchDao.getByGenre(withinGenre, genres);
	}

	@Override
	public Set<Pitch> getPitchesByStoryType(Integer typeId) {
		StoryType type = stDao.getById(typeId);
		return pitchDao.getByStoryType(type);
	}

	@Override
	public Set<Pitch> getPitchesByPitchStage(Integer stageId) {
		PitchStage stage = psDao.getById(stageId);
		return pitchDao.getByPitchStage(stage);
	}

	@Override
	public Set<Pitch> getPitchesByReviewStatus(Integer statusId) {
		ReviewStatus status = rsDao.getById(statusId);
		return pitchDao.getByReviewStatus(status);
	}

	@Override
	public Set<Pitch> getPitchesByPriority(String label) {
		Priority priority = Priority.NORMAL;
		for (Priority p : Priority.values() ) {
			if (p.label == label) {
				priority = p;
			}
		}
		return pitchDao.getByPriority(priority);
	}

	@Override
	public Set<Pitch> getAllPitches() {
		return pitchDao.getAll();
	}

	@Override
	public void updatePitch(Pitch t) throws Exception {
		pitchDao.update(t);
	}

	@Override
	public void deletePitch(Pitch t) {
		pitchDao.delete(t);
	}
	
	// Genre-related
	@Override
	public List<Genre> getAllGenre() {
		return genreDao.getAllOrdered();
	}
	
	// StoryType-related
	@Override
	public List<StoryType> getAllStoryType() {
		return stDao.getAllOrdered();
	}
	
	// PitchStage-related
	@Override
	public List<PitchStage> getAllPitchStage() {
		return psDao.getAllOrdered();
	}
	
	// ReviewStatus-related
	@Override
	public List<ReviewStatus> getAllReviewStatus() {
		return rsDao.getAllOrdered();
	}
	
	// Priority-related
	@Override
	public List<String> getPriorities() {
		List<String> priorities = new ArrayList<>();
		for (Priority p : Priority.values()) {
			priorities.add(p.label);
		}
		return priorities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pitch parseContext(String ctx) {
		System.out.println(ctx);
		ctx = "[" + ctx + "]";
		JSONParser parser = new JSONParser();
		Pitch p = new Pitch();

		try {
			Object obj = parser.parse(ctx);
			JSONArray array = (JSONArray)obj;
			JSONObject jObj = (JSONObject)array.get(0);
			jObj.keySet().forEach( key -> {
				switch (key.toString()) {
					case "additionalFiles":
						Set<AdditionalFile> afs = new HashSet<>();
						JSONArray fileNames = (JSONArray)jObj.get(key);
						System.out.println(fileNames);
						for (Object f : fileNames) {
							AdditionalFile af = new AdditionalFile();
							String path = SpmsAppJavalin.USER_FILE_LOC + "/temp/" + f.toString();
							af.setId(1);
							af.setPath(path);
							try {
								af.setId(afDao.add(af));
							} catch (Exception e) {
								e.printStackTrace();
							}

							afs.add(af);
						}
						p.setAdditionalFiles(afs);
						break;
					case "author":
						JSONObject uObj = (JSONObject) jObj.get(key);
						System.out.println(uObj.toString());
						uObj.keySet().forEach( uKey -> {
							if (uKey.toString().equals("id")) {
								Integer uId = Integer.parseInt(uObj.get(uKey).toString());
								User u = uDao.getById(uId);
								p.setAuthor(u);
							}
						});
						break;
					case "completionDate":
						LocalDate ld = LocalDate.parse(jObj.get(key).toString());
						p.setCompletionDate(ld);
						break;
					case "description":
						p.setDescription(jObj.get(key).toString());
						break;
					case "genre":
						JSONObject gObj = (JSONObject) jObj.get(key);
						System.out.println(gObj.toString());
						gObj.keySet().forEach( gKey -> {
							if (gKey.toString().equals("id")) {
								Integer gId = Integer.parseInt(gObj.get(gKey).toString());
								Genre g = genreDao.getById(gId);
								p.setGenre(g);
							}
						});
						break;
					case "id":
						p.setId(Integer.parseInt(jObj.get(key).toString()));
						break;
					case "pitchMadeAt":
						LocalDateTime ldt = LocalDateTime.parse(jObj.get(key).toString());
						p.setPitchMadeAt(ldt);
						break;
					case "pitchArivedAt":
						LocalDateTime paa = LocalDateTime.parse(jObj.get(key).toString());
						p.setPitchArrivedAt(paa);
						break;
					case "pitchStage":
						JSONObject psObj = (JSONObject) jObj.get(key);
						System.out.println(psObj.toString());
						psObj.keySet().forEach( psKey -> {
							if (psKey.toString().equals("id")) {
								Integer psId = Integer.parseInt(psObj.get(psKey).toString());
								PitchStage ps = psDao.getById(psId);
								p.setPitchStage(ps);
							}
						});
						break;
					case "priority":
						for (Priority priority : Priority.values()) {
							if (priority.label.equals(jObj.get(key).toString())) p.setPriority(priority);
						}
						break;
					case "reviewStatus":
						JSONObject rsObj = (JSONObject) jObj.get(key);
						System.out.println(rsObj.toString());
						rsObj.keySet().forEach( rsKey -> {
							if (rsKey.toString().equals("id")) {
								Integer rsId = Integer.parseInt(rsObj.get(rsKey).toString());
								ReviewStatus rs = rsDao.getById(rsId);
								p.setReviewStatus(rs);
							}
						});
						break;
					case "storyType":
						JSONObject stObj = (JSONObject) jObj.get(key);
						System.out.println(stObj.toString());
						stObj.keySet().forEach( stKey -> {
							if (stKey.toString().equals("id")) {
								Integer stId = Integer.parseInt(stObj.get(stKey).toString());
								StoryType st = stDao.getById(stId);
								p.setStoryType(st);
							}
						});
						break;
					case "tagline":
						p.setTagline(jObj.get(key).toString());
						break;
					case "title":
						p.setTitle(jObj.get(key).toString());
						break;
				}
			});
		} catch (ParseException pe) {
			System.out.println("Position: " + pe.getPosition());
			pe.printStackTrace();
		}
		
		System.out.println(p);
		
		return p;
	}

	@Override
	public String updateFilePaths(String file) {

		String path = SpmsAppJavalin.USER_FILE_LOC + "/temp/" + file;
		
		AdditionalFile af = afDao.getByPath(path);
//		System.out.println(af);
		Pitch p = pitchDao.getByAdditionalFile(af);
//		System.out.println(p);
		
		String updatedPath = SpmsAppJavalin.USER_FILE_LOC + "/pitch_" + p.getId() + "/pitch/";
		File newPath = new File(updatedPath);
		if (!newPath.exists()) {
			Boolean success = newPath.mkdirs();
			if (success) {
				System.out.println("Successfully created new file system");
			} else {
				System.out.println("Failed to create new file system");
			}
		}
		updatedPath += file;
		
		System.out.println(path);
		System.out.println(updatedPath);
		af.setPath(updatedPath);
		try {
			afDao.update(af);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		File original = Paths.get(path).toFile();
		if (original.renameTo(Paths.get(updatedPath).toFile())) {
			original.delete();
			System.out.println("Successfully moved from " + path + " to " + updatedPath);
		} else {
			System.out.println("Failed to move file");
		}
		
		File moved = new File(updatedPath);
		if (moved.equals(null)) {
			String failed = null;
			return failed;
		}
		
		return updatedPath;
	}
	
	@Override
	public Boolean checkShouldHold(Integer id) {
		Pitch p = pitchDao.getById(id);
		Integer total = checkForTotalScore(p);
		if (total + p.getStoryType().getWeight() > 100) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void releaseHold(Pitch t) {
		Integer total = checkForTotalScore(t);
		if (total < 100) {
			Set<Pitch> pitches = pitchDao.getByAuthor(t.getAuthor());
			List<Pitch> pitchList = new ArrayList<Pitch>(pitches);
			pitchList.sort((Pitch p1, Pitch p2) -> {
				if (p1.getPitchMadeAt().isBefore(p2.getPitchMadeAt())) {
					return 1;
				} else if (p1.getPitchMadeAt().isEqual(p2.getPitchMadeAt())){
					return 0;
				} else {
					return -1;
				}
			});
			
			iterate:
			for (Pitch p : pitchList) {
				if (p.getPitchStage().getId() == 1) {
					if (total + p.getStoryType().getWeight() <= 100) {
						advancePitch(p);
						total += p.getStoryType().getWeight();
					} else {
						break iterate;
					}
				}
			}
		}

	}
	
	private Integer checkForTotalScore(Pitch p) {
		User u = p.getAuthor();
		Set<Pitch> pitches = pitchDao.getByAuthor(u);
		Integer total = 0;
		for (Pitch pitch : pitches) {
			if (pitch.getId() == p.getId()) continue;
			if (pitch.getPitchStage().getId() == 5 && pitch.getReviewStatus().getId() >= 5) continue;
			total += pitch.getStoryType().getWeight();
		}
		return total;
	}
	
	@Override
	public Pitch advancePitch(Pitch t) {
		if (t.getPitchStage().getId() < 5) {
			t.setPitchStage(psDao.getById(t.getPitchStage().getId() + 1));
			t.setPitchArrivedAt(LocalDateTime.now());
			try {
				pitchDao.update(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return getPitchById(t.getId());
		} else {
			return t;
		}
	}
	
}
