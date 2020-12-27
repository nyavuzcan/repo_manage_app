import axios from 'axios';
import { setAuthorizationToken } from '../helpers/setAuthorizationToken';

const signup = (name,lastname,email,username, password) => {
    return axios.post("http://localhost:8090/authenticate/register",
        {
            name:name, lastname: lastname, email:email ,username:username, password:password
        })
        .then(rs =>{
            if (rs.data){
            }
            return "Check Your Email For Verify"
        })
        . catch(err => console.log('Signup Error :'+err))

}
const login = (username, password) => {
    return axios.post("http://localhost:8090/authenticate/verify",
        {
        userName:username, password: password
    })
        .then(user =>{

            //if there is user (user.data.status =true )

            if (user.data.jwt){
                let  token  =user.data.jwt;
                localStorage.setItem("jwtToken", token);
                setAuthorizationToken(token);
            }
            return user.data
        })
        . catch(err => console.log('Login Error :'+err))
}

const logout = () => {
    localStorage.removeItem("jwtToken");
    setAuthorizationToken(false);
}

export default {login, logout, signup};