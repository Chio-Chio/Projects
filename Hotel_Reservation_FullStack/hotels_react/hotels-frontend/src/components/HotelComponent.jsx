import React, { useState } from 'react'
import { createHotel } from '../services/HotelService'
import { useNavigate } from 'react-router-dom'

const HotelComponent = () => {

    const [name, setName]  = useState('')
    const [latitude, setLatitude] = useState('')
    const [longitude, setLongitude] = useState('')
    const [feedback, setFeedback] = useState('')

    const navigator = useNavigate();



function handleLongitude(e){
    setLongitude(e.target.value);
}

function saveHotel(e){
    e.preventDefault();

    const hotel = {name, latitude, longitude, feedback}
    console.log(hotel)

    createHotel(hotel).then((response) =>{
        //console.log(response.data())
        navigator('/hotels')
    })
}

  return (
    <div className='container'> <br></br>
        <div className='row'>
            <div className='card col-md-6 offset-md- offset-md-3'>
                <h2 className='text-center'>Add hotel</h2>
                <div className='card-body'>
                    <form>
                        <div className='from-gorup mb-2'>
                            <label className='form-label'>
                                Hotel Name:
                            </label>
                            <input
                                type='text'
                                placeholder='Enter Hotel Name'
                                value={name}
                                className='from-control'
                                onChange={(e)=>{
                                    setName(e.target.value);
                                }}>
                            </input>
                        </div>
                        <div className='from-gorup mb-2'>
                            <label className='form-label'>
                            Latitude:
                            </label>
                            <input
                                type='text'
                                placeholder='Enter latitude'
                                value={latitude}
                                className='from-control'
                                onChange={ (e) =>
                                    setLatitude(e.target.value)}>
                            </input>
                        </div>

                        <div className='from-gorup mb-2'>
                            <label className='form-label'>
                            Longitude:
                            </label>
                            <input
                                type='text'
                                placeholder='Enter Longitude'
                                value={longitude}
                                className='from-control'
                                onChange={handleLongitude}>
                            </input>
                        </div>

                        <div className='from-gorup mb-2'>
                            <label className='form-label'>
                            Feedback:
                            </label>
                            <input
                                type='text'
                                placeholder='Enter feedback'
                                value={feedback}
                                className='from-control'
                                onChange={ (e) =>
                                    setFeedback(e.target.value)}>
                            </input>
                        </div>

                        <button className='btn btn-success' onClick={saveHotel}>
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default HotelComponent

