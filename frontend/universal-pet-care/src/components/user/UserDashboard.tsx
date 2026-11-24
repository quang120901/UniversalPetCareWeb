import React, { useEffect, useState } from "react";
import { Container, Tab, Tabs } from "react-bootstrap";
import { useParams } from "react-router-dom";
import UserProfile from "./UserProfile";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { deleteUserPhoto } from "../modals/ImageUploaderService";

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

    const handleRemovePhoto = async () => {
        try {
            const result = await deleteUserPhoto(user.photoId, user.id);
            setSuccessMessage(result.message);
            setShowSuccessAlert(true);
        } catch (error) {
            setErrorMessage(error.message);
            setShowErrorAlert(true);
            console.error(error.message);
        }
    }

    const handleDeleteAccount = async () => {
        try {
            const response = await deleteUserPhoto(userId)
            setSuccessMessage(response.message);
            setShowSuccessAlert(true);
        } catch (error) {
            setErrorMessage(error.message);
            setShowErrorAlert(true);
            console.error(error.message);
        }
    }

    return (
        <Container>
            <Tabs>
                <Tab eventKey='profile' title={<h3>Profile</h3>}>
                    {user && (
                        <UserProfile user={user} 
                        handleRemovePhoto={handleRemovePhoto}
                        handleDeleteAccount={handleDeleteAccount} />)}
                </Tab>
            </Tabs>
        </Container>
    )
}

export default UserDashboard;