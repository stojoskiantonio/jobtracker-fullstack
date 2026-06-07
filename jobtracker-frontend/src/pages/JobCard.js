function JobCard(props) {

    const colorMap = {
        APPLIED: 'blue',
        INTERVIEW: 'yellow',
        OFFER: 'green',
        REJECTED: 'red'

    }

    return (
    <div>
                    <h3>{props.job.companyName}</h3>
                    <p>{props.job.position}</p>
                    <p style={{color: colorMap[props.job.status]}}>{props.job.status}</p>
                    <button onClick={() => props.onDelete(props.job.id)} >Delete</button>
        </div>
    )
}

export default JobCard;