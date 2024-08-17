import axios from 'axios'
import config from '../config'


export async function getBook(body){
    const response = await axios.get(`${config.url}/book/info/${body}`)
    // console.log(response.data);
    return response.data
}


export async function addBook(body){
    const response = await axios.post(`${config.url}/book/add`, body)
    console.log(response.data);
    return response.data
}

export async function getAllBooks(){
    const response = await axios.get(`${config.url}/book`)
    console.log(JSON.stringify(response))
    return response.data
}

export async function getAllCategories(){
    const response = await axios.get(`${config.url}/categories/getAll`)
    // console.log(JSON.stringify(response.data))
    return response.data
}

export async function deleteBookDetails(body){
    const response = await axios.delete(`${config.url}/book/delete/${body}`)
    console.log(JSON.stringify(response))
    return response.data
}

export async function updateBookDetails(body){
    const response = await axios.patch(`${config.url}/book/update`, body)
    // console.log(JSON.stringify(response))
    return response.data
}

