import React, { useEffect, useState } from "react";
import { Container, Tab, Tabs } from "react-bootstrap";
import { useParams } from "react-router-dom";
import UserProfile from "./UserProfile";
import UseMessageAlerts from "../hooks/UseMessageAlerts";

const UserDashboard = () => {
    const [user, setUser] = useState(null);

    const {
    successMessage,
    setSuccessMessage,
    errorMessage,
    setErrorMessage,
    showSuccessAlert,
    setShowSuccessAlert,
    showErrorAlert,
    setShowErrorAlert,
  } = UseMessageAlerts();

    const { userId } = useParams();

    useEffect(() => {
        const getUser = async () => {
            try {
                const result = await getUserById(userId);
                setUser(result.data);
            } catch (error) {
                setErrorMessage(error.response.data.message);
                setShowErrorAlert(true);
                console.error(error.message);
            }
        }
        getUser();
    }, [userId]);

    return (
        <Container>
            <Tabs>
                <Tab eventKey='profile' title={<h3>Profile</h3>}>
                    {user && (<UserProfile user={user}/>)}
                </Tab>
            </Tabs>
        </Container>
    )
}

export default UserDashboard;