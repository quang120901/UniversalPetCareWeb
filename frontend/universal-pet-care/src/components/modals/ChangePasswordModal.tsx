import React, { useState } from "react";
import { eyeOff } from "react-icons-kit/feather"
import UseMessageAlerts from "../hooks/UseMessageAlerts";

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

    return (
        <div>

        </div>
    )
}

export default ChangePasswordModal;