import { useState } from "react";



function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    // Login handling
    const handleSubmit = async (e) => {
        // Prevents it from losing all the data
        e.preventDefault();

        // POST method, sending the data to the backend
        const response = await fetch('http://localhost:8080/api/users/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({email, password})
        });

        const data = await response.json();

        // Response if it's good
        if (response.ok) {
            localStorage.setItem('token', data.token);
            window.location.href = '/dashboard';
        }

        else {
            alert('Invalid email or password');
        }
    }

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
            <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
                />
            <input
                type="password"
                value={password}
                onChange={(p) => setPassword(p.target.value)}
                placeholder="Password"
                />
            <button type="submit">Login</button>
            </form>
        </div>
    )

    
}

export default LoginPage;
