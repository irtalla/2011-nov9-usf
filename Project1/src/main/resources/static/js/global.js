export let baseUrl = 'http://localhost:8080/html';
export let loggedUser = null;

export function setBaseUrl(newUrl) {
    baseUrl = newUrl;
}

export function setLoggedUser(logged) {
    loggedUser = logged;
}