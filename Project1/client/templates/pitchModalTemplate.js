


const getDraftForPitch = async (pitch) => {
    let response = await fetchDraftByPitchId(pitch.id);
    if (response.status === 200) {
        let draft = JSON.parse(await response.json());
        return draft;
    } else {
        alert("Internal system error: could not get draft by id.");
    }
}


/*
                `
            <input 
            type="text" 
            class="form-control" 
            name="title" 
            value="${draft.content}"
            onInput="handlePitchContentChange(${draft.id})"
            >
            `

*/
const createPitchModalCard = async (pitch) => {

    let draftSpace = ``;
    if (pitch.stage.name.toUpperCase() === "FINAL REVIEW") {
        let draft = await getDraftForPitch(pitch); 
        if ( draft) {
            console.log(draft);
            draftSpace = 
            `
            <input 
            type="text" 
            class="form-control" 
            name="title" 
            value="${draft.content}"
            onInput="handlePitchContentChange(${draft.id})" >
            `;
        }
    }


    let pitchModalCard =
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
    ${draftSpace}
    `;

    return pitchModalCard;




}

