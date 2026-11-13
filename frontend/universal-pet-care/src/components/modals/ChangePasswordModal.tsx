import React, { useState } from "react";
import { eyeOff } from "react-icons-kit/feather"
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { changeUserPassword } from "../user/UserService";

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
        } catch (error) {
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true);
            console.error(error.message);
        }
    }

    return (
        <div>

        </div>
    )
}

export default ChangePasswordModal;