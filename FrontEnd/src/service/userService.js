import axios from 'axios'
import config from '../config'

export async function signup(body){

    // //body parameters
    // const body = {
    //     firstName,
    //     lastName,
    //     email,
    //     password,
    //     passwordConfirm,
    // }

    //make API call
    const response = await axios.post(`${config.url}/user/signup`, body)

    //read JSON data (response)
    return response.data
}


export async function signin(email, password){
    //body parameters
    const body = {
        email,
        password,
    }

    //make API call
    const response = await axios.post(`${config.url}/user/signin`, body)

    //read JSON data (response)
    return response.data
}

export async function getUser(body){
    const response = await axios.get(`${config.url}/user/info/${body}`)

    return response.data
}

export async function getUsers(){
    const response = await axios.get(`${config.url}/user/getall`)

    return response.data
}