
import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import ListEmployee from './components/ListEmployee';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import AddEmployee from './components/AddEmployee';

function App() {
  return (
    <div className="App">
      <Router>
        <HeaderComponent />
        <div className='container'>
          <Routes>
          <Route exact path="/" element={<ListEmployee />} />
          <Route path="/employees" element={<ListEmployee/>} />
          <Route path="/add-employee" element={<AddEmployee/>} />
          <Route path="/edit-employee/:id" element={<AddEmployee/>} />
          </Routes>

        </div>
        <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
