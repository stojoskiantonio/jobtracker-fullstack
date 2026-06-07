import { useState } from "react";


function RegisterPage () {
    const [email, setEmail] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [password, setPassword] = useState('');
    
    const handleSubmit = async (e) => {
        e.preventDefault();

        const response = await fetch('http://localhost:8080/api/users/register', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({email, firstName, lastName, password, role: 'USER'})
        });

        const data = await response.json();

        if (response.ok) {
            window.location.href = '/login';
        }

        else {
            alert(data.message);
        }
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
            <input type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"/>
             <input type="text"
                value={firstName}
                onChange={(f) => setFirstName(f.target.value)}
                placeholder="First Name"/>
             <input type="text"
                value={lastName}
                onChange={(l) => setLastName(l.target.value)}
                placeholder="Last Name"/>
             <input
                type="password"
                value={password}
                onChange={(p) => setPassword(p.target.value)}
                placeholder="Password"
                />
              <button type="submit">Submit</button>
            </form>
        </div>
    ) 
        
    
}

export default RegisterPage;