import React, { useEffect, useState } from "react";
import { getUserById } from "../user/UserService";
import UseMessageAlerts from "../hooks/UseMessageAlerts";

const ImageUploader = () => {
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

    //1. get the user
    //2. check if the user already got a photo
    //3. if yes, the update the existing photo
    //esle, create a new photo for the user
    return (
        <div>

        </div>
    )
}

export default ImageUploader