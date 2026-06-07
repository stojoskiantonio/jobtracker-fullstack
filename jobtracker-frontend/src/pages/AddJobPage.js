import { useState } from "react";

function AddJobPage () {
    const [companyName, setCompanyName] = useState('');
    const [position, setPosition] = useState('');
    const [status, setStatus] = useState('APPLIED');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const token = localStorage.getItem('token');

        const reponse = await fetch ('http://localhost:8080/api/jobs', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({companyName, position, status})
        });

        if (reponse.ok) {
            window.location.href = '/dashboard';
        }

        else {
            alert('Failed to add job');
        }
    }
    
    return (
        <div>
            <h2>Insert Company Name</h2>
            <form onSubmit={handleSubmit}>
            <input type="text" 
            value={companyName} 
            onChange={(c) => setCompanyName(c.target.value)}
            placeholder="Text"/>

            <h2>Write job position</h2>
            <input type="text"
            value={position}
            onChange={(p) => setPosition(p.target.value)}
            placeholder="Text"/>

            <h2>Select status</h2>
            <select value={status}
             onChange={(s) => setStatus(s.target.value)}>
                <option value="APPLIED">Applied</option>
                <option value="INTERVIEW">Interview</option>
                <option value="OFFER">Offer</option>
                <option value="REJECTED">Rejected</option>
             </select>

             <button type="submit">Insert job</button>
             </form>
        </div>
    )
}

export default AddJobPage;