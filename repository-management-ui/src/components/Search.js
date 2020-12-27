import React, { Component } from 'react'
import {searchProduct,getProducts} from "../services/ProductService"


export default class Search extends Component {

    state = {
        searchText:"",
        products: []
    }


    onChange = (e) => {
        const {name,value} = e.target

        this.setState({
            [name]:value
        })
        
        if(value === ""){
            getProducts().then(response =>
                this.setState({
                    products:response.data
                })
                )
        }
        searchProduct(value).then(response => {
            this.setState({
                products:response.data
            })
        })
    }


    onSubmit = () => {
        this.props.searchProducts(this.state.searchText)
        this.setState({
            searchText:""
        })
        
    }


    render() {
        return (  
                <div className="ui category search">
                    <div className="ui icon input">
                        <input className="prompt" type="text" name="searchText" value={this.state.searchText} onChange={this.onChange}/>
                        <i className="search icon"></i>
                    </div>
                    <div className="results"></div>
                    {/* <button className="btn btn-primary" onClick={this.onSubmit} style={{marginLeft:"10px"}}>Ara</button> */}
                </div>
            
        )
    }
}
