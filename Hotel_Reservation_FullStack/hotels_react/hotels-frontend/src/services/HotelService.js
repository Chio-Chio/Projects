import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/hotels';

export const listHotels = () =>{
    return axios.get(REST_API_BASE_URL);
}

export const createHotel = (hotel) => axios.post(REST_API_BASE_URL, hotel);

export const getHotel = (hotelId)  => axios.post(REST_API_BASE_URL+'/'+hotelId);
