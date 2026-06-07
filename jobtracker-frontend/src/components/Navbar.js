import { Link, useNavigate } from "react-router-dom";

function Navbar () {

    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    const token = localStorage.getItem('token');
    if (!token) {
        return (
            <nav>
        <ul>
            <li><Link to="/login">Login</Link></li>
            <li><Link to="/register">Register</Link></li>
        </ul>
        </nav>
        );
    }

    else {
        return (
            <nav>
        <ul>
            <li><Link to ="/dashboard">Dashboard</Link></li>
            <li><Link to ="/addjob">Add Job</Link></li>
            <li><button onClick={handleLogout}>Log Out</button></li>
        </ul>
        </nav>
        );
    }
}

export default Navbar;