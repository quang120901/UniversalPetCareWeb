import React, { useEffect, useState } from "react";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { useParams } from "react-router-dom";
import { getUserById } from "./UserService";

const UserUpdate = () => {

    const [isProcessing, setIsProcessing] = useState(false);
    const { userId } = useParams();
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

    return(
        <div>

        </div>
    )
}

export default UserUpdate;