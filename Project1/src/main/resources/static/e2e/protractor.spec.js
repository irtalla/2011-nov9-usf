const { browser, element } = require("protractor");

// this make it so that protractor doesn't get angry w/ you
// for this project not being an angular project
browser.ignoreSynchronization = true;

describe('protractor example', () => {
    beforeAll( () => {
        browser.get('http://localhost:8080');
    });
});