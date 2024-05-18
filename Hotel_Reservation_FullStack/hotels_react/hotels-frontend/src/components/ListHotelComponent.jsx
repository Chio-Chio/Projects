import React, {useEffect, useState} from 'react'
import { listHotels } from '../services/HotelService'
import { useNavigate } from 'react-router-dom'

const ListHotelComponent = () => {
    const [hotels, setHotels] = useState([])
    const [radius, setRadius]  = useState('')
    const [userLongitude, setUserLongitude]  = useState('')
    const [userLatitude, setUserLatitude]  = useState('')
    const [filteredHotels, setFilteredHotels] = useState([])

    const navigator = useNavigate();

    useEffect(()=>{
        listHotels().then((response) =>{
            setHotels(response.data);
        }).catch(error =>{
            console.error(error);
        })
    }, [])

    const calculateDistance = (lat1, lon1, lat2, lon2) => {
        const toRadians = (degree) => degree * (Math.PI / 180);

        const R = 6371; 
        const dLat = toRadians(lat2 - lat1);
        const dLon = toRadians(lon2 - lon1);

        const a = 
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) * 
            Math.sin(dLon / 2) * Math.sin(dLon / 2); 

        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); 

        return R * c; 
    }

    const filterHotelsByRadius = () => {
        if (!radius || !userLatitude || !userLongitude) return;

        const filtered = hotels.filter(hotel => {
            const distance = calculateDistance(userLatitude, userLongitude, hotel.latitude, hotel.longitude);
            return distance <= radius;
        });

        setFilteredHotels(filtered);
    }

    useEffect(() => {
        filterHotelsByRadius();
    }, [radius, userLatitude, userLongitude, hotels]);

    function addNewHotel(){
        navigator('/add-hotel')
    }

    

    return (
        <div className='container overflow-auto'>
            <h2 className='text-center'>List of Hotels</h2>
            <button className="btn btn-success" onClick={addNewHotel}>Add Hotel</button>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Hotel Id</th>
                        <th>Hotel Name</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                        <th>Feedback</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        hotels.map(hotel => 
                            <tr key={hotel.id}>
                                <td>{hotel.id}</td>
                                <td>{hotel.name}</td>
                                <td>{hotel.latitude}</td>
                                <td>{hotel.longitude}</td>
                                <td>{hotel.feedback}</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
            <h2 className='text-center'>Pick radius, latiude, longitude:</h2>            <input
                type='text'
                placeholder='Enter Radius'
                value={radius}
                className='form-control'
                onChange={(e)=>{
                    setRadius(e.target.value);
                }}>
            </input>

            <input
                type='text'
                placeholder='Enter user Latitude'
                value={userLatitude}
                className='form-control'
                onChange={(e)=>{
                    setUserLatitude(e.target.value);
                }}>
            </input>

            <input
                type='text'
                placeholder='Enter user Longitude'
                value={userLongitude}
                className='form-control'
                onChange={(e)=>{
                    setUserLongitude(e.target.value);
                }}>
            </input>

            <h2 className='text-center'>Filtered Hotels</h2>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Hotel Id</th>
                        <th>Hotel Name</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                        <th>Feedback</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        filteredHotels.map(hotel => 
                            <tr key={hotel.id}>
                                <td>{hotel.id}</td>
                                <td>{hotel.name}</td>
                                <td>{hotel.latitude}</td>
                                <td>{hotel.longitude}</td>
                                <td>{hotel.feedback}</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListHotelComponent
