package com.revature.services;

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

import com.revature.data.AdditionalFileDAO;
import com.revature.data.AdditionalFileDAOFactory;
import com.revature.data.GenreDAO;
import com.revature.data.GenreDAOFactory;
import com.revature.data.PitchDAO;
import com.revature.data.PitchDAOFactory;
import com.revature.data.PitchStageDAO;
import com.revature.data.PitchStageDAOFactory;
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
import com.revature.models.Role;
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
	public Set<Pitch> getPitchesByAuthor(User author) {
		return pitchDao.getByAuthor(author);
	}

	@Override
	public Set<Pitch> getPitchesByGenre(Integer genre_id, Boolean withinGenre) {
		Genre genre = genreDao.getById(genre_id);
		
		return pitchDao.getByGenre(genre, withinGenre);
	}

	@Override
	public Set<Pitch> getPitchesByStoryType(StoryType type) {
		return pitchDao.getByStoryType(type);
	}

	@Override
	public Set<Pitch> getPitchesByPitchStage(PitchStage stage) {
		return pitchDao.getByPitchStage(stage);
	}

	@Override
	public Set<Pitch> getPitchesByReviewStatus(ReviewStatus status) {
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
							String path = "src/main/resources/files/pitch_0/initial/" + f.toString();
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
						User u = new User();
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
	public void updateFilePaths(Integer id) {
		Pitch p = this.getPitchById(id);
		if (p.getAdditionalFiles() == null || p.getAdditionalFiles().size() == 0) return;
		
		for (AdditionalFile af : p.getAdditionalFiles()) {
			String path = af.getPath();
			int index = path.indexOf('0');
			path = path.substring(0,index) + p.getId() + path.substring(index + 1);
			af.setPath(path);
			try {
				afDao.update(af);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			this.updatePitch(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
