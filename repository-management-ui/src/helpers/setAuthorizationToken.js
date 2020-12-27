import axios from "axios";


//for Private api request header token control
export const setAuthorizationToken  = token => {
    if (token) {
        axios.defaults.headers.common["Authorization"] ='Bearer '+token;
    }
    else {
        delete axios.defaults.headers.common["Authorization"];
    }

}