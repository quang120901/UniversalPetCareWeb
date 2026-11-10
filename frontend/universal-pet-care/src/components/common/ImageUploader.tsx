import React, { useEffect, useState } from "react";
import { getUserById } from "../user/UserService";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { Button, Form, InputGroup, Modal } from "react-bootstrap";

const ImageUploader = ({ userId, show, handleClose}) => {
    const [file, setFile] = useState(null);
    const [user, setUser] = useState(null);
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


    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    }

    const getUser = async () => {
        try {
            const result = getUserById(userId);
            setSuccessMessage(result.data);
        } catch (error) {
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true);
            console.error(error.message);
        }
    }

    useEffect(() => {
        getUser();
    }, [userId])

    const handleImageUpload = async (e) => {
        e.preventDefault();

        try {
            const formData = new FormData();
            formData.append("file", file);

            if(user && user.photo) {
                const reader = new FileReader();
                reader.readAsArrayBuffer(file);
                reader.onload = async (e) => {
                    const fileBytes = new Uint8Array(e.target.result)
                    const response = await updateUserPhoto(user.photoId, fileBytes)
                    setSuccessMessage(response.data)
                    window.location.reload();
                    setShowSuccessAlert(true);
                }
            } else {
                const response = await uploadUserPhoto(userId, fileBytes);
                setSuccessMessage(response.data);
                window.location.reload();
                setShowSuccessAlert(true);
            }
        } catch (error) {
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true);
            console.error(error.message);
        }
    }
    //1. get the user
    //2. check if the user already got a photo
    //3. if yes, the update the existing photo
    //esle, create a new photo for the user
    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>Upload a Photo</Modal.Header>
            <Modal.Body>
                <Form>
                    <InputGroup>
                        <Form.Control type="file">
                            <Button variant="secondary" onClick={handleImageUpload}>Upload</Button>
                        </Form.Control>
                    </InputGroup>
                </Form>
            </Modal.Body>
        </Modal>
    )
}

export default ImageUploader