import React, { useEffect, useState } from "react";
import {Image} from "antd";
import axios from "axios";

export default function Product(props) {

    const {imagePath,name,entryDate,exitDate,productNumber,updateProduct,deleteProduct} = props

    return (
           <div className="product">
                <div className="ui unstackable items">
                    
                <div className="item" style={{marginTop:"20px"}}>
                 
                    <div className="image">
                        <img src={imagePath} alt={"no"} style={{borderRadius:"10px"}}/>
                    </div>
                    <div className="content">
                    <a className="header">{name}</a>
                    <div className="product-info">
                    <div className="extra">
                        Adet: {productNumber}
                    </div>
                    <div className="extra"> 
                        Ürün Giriş Tarihi: {entryDate}
                    </div>
                    <div className="extra">
                        Ürün Çıkış Tarihi: {exitDate}
                    </div>
                    </div>
                    </div>
                    
                     </div>
                     <div className="buttons">
                     <button className="ui teal button" onClick={updateProduct}>
                        Düzenle
                    </button>
                    <button className="ui red button" onClick={deleteProduct}>
                        Sil
                    </button>
                    </div>
                </div>
            </div>
        
    )
}
