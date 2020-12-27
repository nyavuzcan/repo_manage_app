import React, { Component } from 'react'
import Input from "../components/Input"
import {getProductById,updateProduct} from "../services/ProductService"
import axios from "axios"

export default class UpdateProductPage extends Component {
    state = {
        id: this.props.match.params.id,
        name:null,
        productNumber:null,
        imagePath:null,
        entryDate:null,
        exitDate:null,
        productImage:null,
    }

    onChange = (e) => {
        const {name,value} = e.target

        this.setState({
            [name]:value
        })
    }

    handleImageChange = (e) => {
        e.preventDefault();
        this.setState({
            productImage: e.target.files[0]
        }, () => {

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
                })

                .catch(err => console.log(err))
        })

    };

    componentDidMount(){
        getProductById(this.state.id).then(response => {
            let product = response.data
            this.setState({
                name: product.name,
                productNumber: product.productNumber,
                entryDate:product.entryDate,
                imagePath:product.imagePath,
                exitDate:product.exitDate
            })
        }          
    )
    }
    
    updateProduct = (e) => { 
        e.preventDefault();
        let product = {id: this.state.id, name: this.state.name, productNumber: this.state.productNumber,
            imagePath: this.state.imagePath, entryDate: this.state.entryDate, exitDate:this.state.exitDate}
        updateProduct(product).then(response =>
            this.props.history.push("/products")
        )
    };

    render() {
        return (
            <div className="product-form" onSubmit={e=>this.updateProduct(e)}>
            <form>
                <h1 style={{textAlign:"center"}}>Edit Product</h1>
                    <img src={this.state.imagePath} style={{width:"100px",height:"100px"}}/>
                    <Input label="Pick an image" type="file"   name="productImage" onChange={this.handleImageChange} required={false}/>
                    <Input label="Product name"  required={true} type="text" value={this.state.name} className="form-control" name="name" onChange={this.onChange} />
                    <Input label="Product number"  required={true} type="text" value={this.state.productNumber} className="form-control" name="productNumber" onChange={this.onChange} />
                    <div className="button" style={{display:"flex",justifyContent:"center"}}>
                    <button className="btn btn-primary" type="submit">
                        Düzenle
                    </button>            
                    </div>
            </form>
        </div>
        )
    }
}
