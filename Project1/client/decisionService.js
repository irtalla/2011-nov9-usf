
const postDecision = async (pitchId, typeId) => {
    alert(`making a decision of type ${typeId} for pitch ${pitchId}`); 
    alert(document.getElementById('explanation-draft-area').value);
    const decision = {
        editorId: currentUser.id,
        pitchId: pitchId,
        decisionType: {
            id: typeId
        },
        explanation: ""

    }
}
