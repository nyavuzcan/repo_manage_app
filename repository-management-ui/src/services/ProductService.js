import axios from "axios"


const PRODUCTS_API_URL = "http://localhost:8090/product"

export const addProduct = (body) => {
    return axios.post(PRODUCTS_API_URL+"/add",body)
}

export const getProducts = () => {
    return axios.get(PRODUCTS_API_URL+"/getProducts")
}

export const getProductById = (id) => {
    return axios.get(PRODUCTS_API_URL+"/getProduct/"+id)
}

export const deleteProduct = (id) => {
    return axios.get(PRODUCTS_API_URL+"/delete/"+id)
}

export const updateProduct = (body) => {
    return axios.post(PRODUCTS_API_URL+"/update",body)
}

export const searchProduct = (productName) => {
    console.log(productName)
    return axios.get(PRODUCTS_API_URL+"/search/"+productName)

}