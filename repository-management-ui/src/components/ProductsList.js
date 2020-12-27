import React, { Component } from 'react'
import {getProducts, deleteProduct,searchProduct} from "../services/ProductService"
import Product from "./Product"
import Search from "./Search"


export default class ProductsList extends Component {

    state = {
        products:[
          
        ]
    }

    onSearchChange = (e) => {
        const {name,value} = e.target

        this.setState({
            [name]:value
        })
        
        
        if(value.charAt(0) == "" || value.charAt(0) == " "){
            getProducts().then(response =>
                this.setState({
                    products:response.data
                })
                )
        }
        else{
        searchProduct(value).then(response => {
            this.setState({
                products:response.data
            })
        })
    }
    }

    searchProducts = (seachText) => {
        getProducts().then(response => 
            console.log(response.data)
            )
    }
     

    componentDidMount(){
        getProducts().then(response => 
            this.setState({
                products: response.data
            }))
    }

    updateProduct = (id) => {

        this.props.history.push(`/update-product/${id}`)
    }

    deleteProduct = (id) => {
        deleteProduct(id).then(respone =>
            this.componentDidMount()
        )

    }

    render() {
        return (            

            <div className="product-list">

                <div className="ui category search">
                    <div className="ui icon input">
                        <input className="prompt" type="text" name="searchText" value={this.state.searchText} onChange={this.onSearchChange}/>
                        <i className="search icon"></i>
                    </div>
                    <div className="results"></div>
                    {/* <button className="btn btn-primary" onClick={this.onSubmit} style={{marginLeft:"10px"}}>Ara</button> */}
                </div>

                {this.state.products.map(product => {
                return <Product key = {product.id}  imagePath={product.imagePath} name={product.name} entryDate={product.entryDate} exitDate={product.exitDate} productNumber={product.productNumber}
                updateProduct ={() => this.updateProduct(product.id)} deleteProduct={() => this.deleteProduct(product.id)}/>

                }
                )}

            </div>
            
        )
    }
}
