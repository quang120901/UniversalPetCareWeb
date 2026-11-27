import React, { useEffect, useState } from "react";
import { Card, Col, Container, Row, Tab, Tabs } from "react-bootstrap";
import { useParams } from "react-router-dom";
import UserProfile from "./UserProfile";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { deleteUserPhoto } from "../modals/ImageUploaderService";
import AlertMessage from "../common/AlertMessage";
import Review from "../review/Review";
import UserAppointments from "../appointment/UserAppointments";

const UserDashboard = () => {
    const [user, setUser] = useState(null);
    const [appointments, setAppointments] = useState([]);

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
                console.log("The user id from the dashboard: ", userId);
                const data = await getUserById(userId);
                setUser(data.data);
                setAppointments(data.data.appointments)
                console.log("The user data from the dashboard: ", data.data)
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
            {showErrorAlert && (<AlertMessage type={"danger"} message={errorMessage} />)}

            {showSuccessAlert && (
                <AlertMessage type={"success"} message={successMessage} />
            )}
            <Tabs className="mb-2" justify>
                <Tab eventKey='profile' title={<h3>Profile</h3>}>
                    {user && (
                        <UserProfile user={user} 
                        handleRemovePhoto={handleRemovePhoto}
                        handleDeleteAccount={handleDeleteAccount} />)}
                </Tab>
                <Tab eventKey='status' title={<h3>Appointments</h3>}></Tab>
                <Tab eventKey='appointments' title={<h3>Appointment Details</h3>}></Tab>
                    <Row>
                        <Col>
                            {user && (
                                <React.Fragment>
                                    {appointments && appointments.length > 0 ? (
                                        <UserAppointments 
                                        user={user}
                                        appointments={appointments} />    
                                    ) : (
                                        <p>No data</p>
                                    )}
                                </React.Fragment>
                            )}
                        </Col>
                    </Row>
                <Tab eventKey='reviews' title={<h3>Reviews</h3>}>
                        <Container className="d-flex justify-content-center align-items-center">
                            <Card className="mt-5 mb-4 review-card">
                                <h4 className="text-center mb-2">Your Reviews</h4>
                                <hr/>
                                
                                <Row>
                                    <Col>
                                        {user && user.reviews && user.reviews.length > 0 ? (
                                            user.reviews.map((review, index) => (
                                                <Review key={index} review={review} />
                                            ))

                                        ) : (<p>No reviews found at this time</p>)}
                                    </Col>
                                </Row>
                            </Card>
                        </Container>
                </Tab>
            </Tabs>
        </Container>
    )
}

export default UserDashboard;