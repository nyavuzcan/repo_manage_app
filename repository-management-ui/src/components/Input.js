import React from 'react'

export default function Input(props) {

    const {label,type,name,className,value,onChange,required} = props
    
    return (
                 <div className="form-group">
                    <label>{label}</label>
                    <input type={type} className={className} value={value} name={name} onChange={onChange} required={required} />
                </div>    
    )
}
