import { useState, useEffect } from "react";
import JobCard from "./JobCard";

function DashboardPage() {
    const [jobs, setJobs] = useState([]);
    const [filter, setFilter] = useState('ALL');

    // Gets the jobs
    useEffect(() => {
        fetchJobs();
    }, []);

    // Stores the jobs in local storage
    const fetchJobs = async () => {
        const token = localStorage.getItem('token');

        if (!token) {
            window.location.href = '/login';
            return; 
        }

        const response = await fetch('http://localhost:8080/api/jobs', {
            headers: { 'Authorization' : `Bearer ${token}`}
        });

        const data = await response.json();
        setJobs(data);
    };

    const deleteJob = async (id) => {
        const token = localStorage.getItem('token');

        await fetch(`http://localhost:8080/api/jobs/${id}`, {
            method: 'DELETE',
            headers: {'Authorization': `Bearer ${token}`}
        });

        fetchJobs();
    }

    
        let filteredJobs;
        if (filter === 'ALL') {
            filteredJobs = jobs;
        }

        else {
            filteredJobs = jobs.filter(job => job.status === filter);
        }
    
    

    // Puts the jobs in new div using map
    return (
        <div>
            <button onClick={() => setFilter('ALL')}>All</button>
            <button onClick={() => setFilter('APPLIED')}>Applied</button>
            <button onClick={() => setFilter('INTERVIEW')}>Interview</button>
            <button onClick={() => setFilter('OFFER')}>Offer</button>
            <button onClick={() => setFilter('REJECTED')}>Rejected</button>
            <p>
            Applied: {jobs.filter(job => job.status === 'APPLIED').length} | 
            Interview: {jobs.filter(job => job.status === 'INTERVIEW').length} |
            Offer: {jobs.filter(job => job.status === 'OFFER').length} |
            Rejected: {jobs.filter(job => job.status === 'REJECTED').length}

             
            
        </p>
            <h2>My job applications</h2>
            {filteredJobs.map(job => (
                    <JobCard key={job.id} job={job} onDelete={deleteJob} />
            ))}
        </div>
    )
}

export default DashboardPage;