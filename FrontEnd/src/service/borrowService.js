import axios from "axios"
import config from "../config"

export async function borrowBook(body){
    const response = await axios.post(`${config.url}/borrow/issue`, body)

    return response.data
}

export async function DashboadDetails(){
    const response = await axios.get(`${config.url}/borrow/list`)
    // console.log(JSON.stringify(response))
    return response.data
}

export async function getAllBorrowDetails(){

    const response = await axios.get(`${config.url}/borrow`)
    console.log(JSON.stringify(response))
    return response.data
}

export async function getBorrowDetails(body){
    console.log(JSON.stringify(body))
    
    const response = await axios.get(`${config.url}/borrow/info/${body}`)
    // console.log(JSON.stringify(response))
    return response.data
}

export async function returnBook(body){
    console.log(JSON.stringify(body))
    const response = await axios.post(`${config.url}/borrow/return`, body)
    // console.log(JSON.stringify(response))
    return response.data
}

export async function getDefaultersList(){
    const response = await axios.get(`${config.url}/borrow/defaulters`)
    // console.log(JSON.stringify(response))

    return response.data
}