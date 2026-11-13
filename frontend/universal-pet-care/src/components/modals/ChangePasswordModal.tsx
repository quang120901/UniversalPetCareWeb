import React, { useState } from "react";
import { eyeOff } from "react-icons-kit/feather"
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { changeUserPassword } from "../user/UserService";
import { Button, Form, InputGroup, Modal } from "react-bootstrap";
import Icon from "react-icons-kit";

const ChangePasswordModal = () => {
    const [type, setType] = useState("password")
    const [icon, setIcon] = useState(eyeOff)
    const [passwords, setPasswords] = useState({
        currentPassword : "",
        newPassword : "",
        confirmNewPassword : "",
    })

    const {
        successMessage,
        setSuccessMessage,
        showSuccessAlert,
        setShowSuccessAlert,
        errorMessage,
        setErrorMessage,
        showErrorAlert,
        setShowErrorAlert,
    } = UseMessageAlerts();

    const handleInputChange = (e) =>{
        const { name, value } = e.target;
        setPasswords((prevState) => ({
            ...prevState,
            [name]: value,
        }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        const { currentPassword, newPassword, confirmNewPassword } = passwords;

        try {
            const response = await changeUserPassword(userId, passwords);
            setSuccessMessage(response.message);
            setShowSuccessAlert(true);
            handleReset();
        } catch (error) {
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true);
            console.error(error.message);
        }
    }

    const handleShowPassword = () => {
        if (type === "password") {
            setType("text");
            setIcon(eyeOn);
        } else {
            setType("password");
            setIcon(eyeOff)
        }
    }

    const handleReset = () => {
        setPasswords({
            currentPassword : "",
            newPassword : "",
            confirmNewPassword : "",
        });
        setShowErrorAlert(false);
        setShowSuccessAlert(false);
    }

    return (
        <Modal>
            <Modal.Header>
                <Modal.Title>Change Password</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group controlId="currentPassword">
                        <Form.Label>Current Password:</Form.Label>
                        <InputGroup>
                            <Form.Control
                                type={type}
                                name='currentPassword'
                                value={currentPassword}
                                onChange={handleInputChange}
                            />
                            <InputGroup.Text onClick={handleShowPassword}>
                                <Icon icon={icon} />
                            </InputGroup.Text>
                        </InputGroup>
                    </Form.Group>

                    <Form.Group controlId="newPassword" className="mb-2">
                        <Form.Label>New Password:</Form.Label>
                        <Form.Control
                            type={type}
                            name='newPassword'
                            value={newPassword}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                     <Form.Group controlId="confirmNewPassword" className="mb-2">
                        <Form.Label>Confirm New Password:</Form.Label>
                        <Form.Control
                            type={type}
                            name='confirmNewPassword'
                            value={confirmNewPassword}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <div className="d-flex justify-content-center mt-4">
                        <div className="mx-2">
                            <Button variant="secondary" size="sm" type="submit">
                                Change Password
                            </Button>
                        </div>
                         <div className="mx-2 mb-4">
                            <Button variant="danger" size="sm" onClick={handleResetForm}>
                                Reset
                            </Button>
                        </div>
                    </div>
                </Form>
            </Modal.Body>
        </Modal>
    )
}

export default ChangePasswordModal;