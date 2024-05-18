
import './App.css'
import { FooterComponent } from './components/FooterComponent'
import { HeaderComponent } from './components/HeaderComponent'
import HotelComponent from './components/HotelComponent'
import ListHotelComponent from './components/ListHotelComponent'
import { BrowserRouter, Route, Routes } from 'react-router-dom'


function App() {

  return (
    <>
    <BrowserRouter>
      <HeaderComponent />
        <Routes>
          {/* // http://localhost:3000 */}
          <Route path='/' element = {<ListHotelComponent/>}></Route>
          {/* // http://localhost:3000/hotels */}
          <Route path='/hotels' element = {<ListHotelComponent/>}></Route>
          <Route path='/add-hotel' element={<HotelComponent/>}></Route>
          
        </Routes>
      <FooterComponent/>
    </BrowserRouter>
    </>
  )
}

export default App
