
const populateModalWithData = () => {

    document.getElementById('exampleModalLabel').innerHTML = 'make it happen'; 
}; 




/**
 * 
 * quick and dirty way to generate html templates:
 * https://stackoverflow.com/questions/18673860/defining-a-html-template-to-append-using-jquery
 * 
 */

const loadArticleCard = () => {

//     const Item = ({ url, img, title }) => `
//     <a href="${url}" class="list-group-item">
//       <div class="image">
//         <img src="${img}" />
//       </div>
//       <p class="list-group-item-text">${title}</p>
//     </a>
//   `;

let generic_image_src = `https://thumbs.dreamstime.com/t/white-feather-quill-pen-glass-inkwell-old-glass-ink-pen-feather-well-quill-image-106467206.jpg`;
let pitch_title, tag_line, genre, form, completion_date, image_src; 

const articleCard =
  `<div class="col-md-4">
  <div class="card">
    <img src="${image_src || generic_image_src } class="card-img-top" alt="..." />
    <div class="card-body">
      <h5 class="card-title"> ${pitch_title || 'no title found'} </h5>
      <p class="card-text">
        ${ tag_line || `(Tag Line) Some quick example text to build on the card title and make up
        the bulk of the card's content.`}
      </p>
    </div>
    <div class="progress">
      <div class="progress-bar bg-warning" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Genre: ${genre || 'genre unspecified'} </li>
      <li class="list-group-item">Form: ${form || 'form unspecified'} </li>
      <li class="list-group-item">Completion Deadline: ${completion_date || 'completion date unspecified'} </li>
    </ul>
    <div class="card-body">
      <a href="#" class="card-link">Card link</a>
      <a href="#" class="card-link">Another link</a>
    </div>
  </div>
</div>`; 

document.getElementById('main-data-display-row').innerHTML += articleCard; 


}

/*


title
tag line
completion date
genre
type ( novel, novella, short story, article )


*/