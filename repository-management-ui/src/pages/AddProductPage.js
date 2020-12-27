import React, { Component } from 'react'
import Input from "../components/Input"
import {addProduct} from "../services/ProductService"
import axios from "axios"

export default class AddProductPage extends Component {

    state = {
        name:null,
        productNumber:null,
        productImage:null,
        imagePath:null
    }

    onChange = (e) => {
        const {name,value} = e.target

        this.setState({
            [name]:value
        })
    }

    handleImageChange = (e) => {
        this.setState({
          productImage: e.target.files[0]
        })
      };

    addProduct = (e) => {
        e.preventDefault();
        let form_data = new FormData();
        form_data.append('file', this.state.productImage);
        let url = 'http://localhost:8090/files';
        axios.put(url, form_data, {
        headers: {
            'content-type': 'multipart/form-data'
        }
     })
        .then(res => {
            this.setState({
                imagePath: res.data.fileDownloadUri
            })
            let product = {name:this.state.name, productNumber:this.state.productNumber, imagePath:this.state.imagePath}
            addProduct(product).then(response => 
            this.props.history.push("/products")
            )
        })
        
        .catch(err => console.log(err))


    };

    render() {
        return (
            <div className="product-form" onSubmit={e=>this.addProduct(e)}>
            <form >
                <h1 style={{textAlign:"center"}}>Add Product</h1>
                    <Input label="Pick an image" type="file" className="" name="productImage" onChange={this.handleImageChange} required={true}/>
                    <Input label="Product name" type="text" className="form-control" name="name" onChange={this.onChange} required={true}/>
                    <Input label="Product number" type="text" className="form-control" name="productNumber" onChange={this.onChange} required={true}/>
                    <div className="button" style={{display:"flex",justifyContent:"center"}}>
                        <button type="submit" className="btn btn-primary" >Ekle</button>
                    </div>          
            </form>
        </div>
        )
    }
}
