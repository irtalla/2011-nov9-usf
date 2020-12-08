const {browser, element} = require('protractor');

browser.ignoreSynchronization = true;

describe ('protractor example', ()=> {
    beforeAll(()=>{browser.get('http://localhost:8080');});
    
});