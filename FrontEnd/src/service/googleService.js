import axios from "axios";
import config from "../config";


export async function getBookByCategory(categoryName){

    const string = categoryName.toLowerCase().replaceAll(' ', '+');

    const response = await axios.get(`${config.googleURL}/books/v1/volumes?q=subject:${string}`)
    // console.log(response.data)

    return response.data
}