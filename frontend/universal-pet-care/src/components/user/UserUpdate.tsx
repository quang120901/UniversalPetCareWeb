import React, { useEffect, useState } from "react";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { useNavigate, useParams } from "react-router-dom";
import { getUserById, updateUser } from "./UserService";
import { Card, Col, Container, Form } from "react-bootstrap";

const UserUpdate = () => {

    const [isProcessing, setIsProcessing] = useState(false);
    const { userId } = useParams();
    const navigate = useNavigate();

    const [userData, setUserData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        userType: "",
        gender: "",
        phoneNumber: "",
        specialization: "",
    });
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

    useEffect(() => {
        const getUserData = async () => {
            try {
                const data = await getUserById(userId);
                setUserData(data.data);
            }   catch (error) {
                setErrorMessage(error.message);
            }
        };

        getUserData();
    }, [userId]);

    const handleUserInputChange = (e) => {
        const { name, value } = e.target;
        setUserData((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleUserUpdate = async (e) => {
        e.preventDefault();
        const updatedUserData = {
            firstName: userData.firstName,
            lastName: userData.lastName,
            gender: userData.gender,
            phoneNumber: userData.phoneNumber,
            email: userData.email,
            UserType: userData.userType,
            specialization: userData.specialization,
        };
        try {
          setIsProcessing(true);
          const response = await updateUser(updatedUserData, userId);
          setSuccessMessage(response.message);
          setShowSuccessAlert(true);
        } catch (error) {
          setErrorMessage(error.message);
          setShowErrorAlert(true);
        } finally {
            setIsProcessing(false);
        }
      };

      const handleCancelEdit = () => {
        navigate(`/user-dashboard/${userId}/my-dashboard`);
      }

    return(
        <Container>
            <div>
                <Form>
                    <div>
                        <Card>
                            <Card.Header></Card.Header>
                            <Card.Body>
                                <fieldset className="field-set">
                                    <legend>Full Name</legend>
                                    <Form.Group
                                        as={Col}
                                        controlId="nameFields"
                                        className="mb-2 d-flex">
                                            <Form.Control
                                                type="text"
                                                name="firstName"
                                                value={userData.firstName}
                                                onChange={handleUserInputChange}
                                                style={{ marginRight: "10px" }}/>
                                        </Form.Group>
                                </fieldset>
                            </Card.Body>
                        </Card>
                    </div>
                </Form>
            </div>
        </Container>
    )
}

export default UserUpdate;