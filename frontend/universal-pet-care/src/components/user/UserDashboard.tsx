import React, { useState } from "react";
import { Container } from "react-bootstrap";
import { useParams } from "react-router-dom";

const UserDashboard = () => {
    const [user, setUser] = useState(null);
    const { userId } = useParams();

    return (
        <Container>
            
        </Container>
    )
}

export default UserDashboard;