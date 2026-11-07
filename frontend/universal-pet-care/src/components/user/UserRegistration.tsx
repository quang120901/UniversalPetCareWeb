import React, { useState } from "react";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { Card, Col, Container, Form, Row } from "react-bootstrap";

const UserRegistration = () => {
    const [user, setUser] = useState({
        firstName: "",
        lastName: "",
        gender: "",
        phoneNumber: "",
        email: "",
        password: "",
        userType: "",
        specialization: ""
    });

    const {
        successMessage,
        setsuccessMessage,
        errorMessage,
        setErrorMessage,
        showSuccessAlert,
        setShowSuccessAlert,
        showErrorAlert,
        setShowErrorAlert,
    } = UseMessageAlerts();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUser((prevState) => ({
            ...prevState,
            [name]: value,
            }));
        };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await registerUser(user);
            setsuccessMessage(response.message);
            setShowSuccessAlert(true);
            handleReset();
        } catch (error) {
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true)
        }
    };

    const handleReset = () => {
        setFormData({
            firstName: "",
            lastName: "",
            gender: "",
            phoneNumber: "",
            email: "",
            password: "",
            userType: "",
            specialization: "",
        });
        setShowSuccessAlert(true);
    }

    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col xs={12} md={8} lg={6}>
                    <Form onSubmit={handleSubmit}>
                        <Card className="shadow mb-5">
                            <Card.Header className="text-center">
                                User Registration Form
                            </Card.Header>
                            <Card.Body>
                                <fieldset>
                                    <legend>Full Name</legend>
                                    <Row>
                                        <Col xs={6} className="mb-2 mb-sm-0">
                                            <Form.Control
                                                type="text"
                                                name="firstName"
                                                placeholder="First Name"
                                                value={user.firstName}
                                                onChange={handleInputChange}
                                                required
                                            />
                                        </Col>
                                        <Col xs={6}>
                                            <Form.Control
                                                type="text"
                                                name="lastName"
                                                placeholder="Last name"
                                                value={user.lastName}
                                                onChange={handleInputChange}
                                                required
                                            />
                                        </Col>
                                    </Row>
                                </fieldset>
                            </Card.Body>
                        </Card>
                    </Form>
                </Col>
            </Row>
        </Container>
    )
}