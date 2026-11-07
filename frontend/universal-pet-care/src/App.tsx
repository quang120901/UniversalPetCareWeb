import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import Home from './components/home/Home'
import BackgroundImageSlider from './components/common/BackgroundImageSlider'
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom'
import RootLayout from './components/layout/RootLayout'
import VeterinarianListing from './components/veterinarian/VeterinarianListing'
import UserRegistration from './components/user/UserRegistration'

function App() {
  const router = createBrowserRouter(createRoutesFromElements(
    <Route path='/' element={<RootLayout />}>
      <Route index element={<Home />} />
      <Route path='/doctors' element={<VeterinarianListing />} />
      <Route path='/register-user' element={<UserRegistration />} />
    </Route>
    )
  );
  return (
    <main className="">
      <RouterProvider router={router} />
    </main>
  );
}

export default App
