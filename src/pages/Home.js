import React from 'react';
import Header from '../components/Header/Header';
import Hero from '../components/Hero/Hero';
import About from '../components/About/About';
import Services from '../components/Services/Services';
import Rooms from '../components/Rooms/Rooms';
import Booking from '../components/Booking/Booking';
import Location from '../components/Location/Location';
import Footer from '../components/Footer/Footer';

const Home = () => {
  return (
    <div className="home-page">
      <Header />
      <Hero />
      <About />
      <Services />
      <Rooms />
      <Booking />
      <Location />
      <Footer />
    </div>
  );
};

export default Home;