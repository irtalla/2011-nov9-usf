

const createPitchModalCard = (pitch) =>
`
<p>
Status: ${pitch.status.name} |
Stage: ${pitch.stage.name} |
Priority: ${pitch.priorityLevel.name}
<p>
<p>
Created: ${pitch.createdTime} |
LastModified: ${pitch.lastModifiedTime} |
Due: ${pitch.deadline}
<p>
<input 
type="text" 
class="form-control" 
name="title" 
value="${pitch.title}"
onInput="handlePitchContentChange(${pitch.id})"
>
<input 
type="text" 
class="form-control" 
name="tagline" 
value="${pitch.tagline}"
onInput="handlePitchContentChange(${pitch.id})"
>
<h3>Title: ${pitch.title} </h3>
<h5>Tagline: ${pitch.tagline} </h5>
<h5>Genre: ${pitch.genre.name} </h5>
<h5>Form: ${pitch.form.name} </h5>
`;
