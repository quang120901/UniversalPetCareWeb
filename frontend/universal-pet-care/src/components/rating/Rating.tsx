import React, { useState } from "react";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { Form } from "react-bootstrap";

const Rating = ({veterinarianId, onReviewSubmit}) => {
    const [hover, setHover] = useState(null);
    const [reviewInfo, setReviewInfo] = useState({
        rating: null,
        feedback: null,
    })
    const { successMessage,
        errorMessage,
        setSuccessMessage,
        setErrorMessage,
        showSuccessAlert,
        showErrorAlert,
        setShowSuccessAlert,
        setShowErrorAlert,
    } = UseMessageAlerts();

    const handleInputChange = (e) => {
        const {name, value} = e.target.value;
        setReviewInfo((prevState) => ({
            ...prevState,
            [name]:value,
        }));
    }

    const handleSubmit = async (e) => {
        e.preventdefault();

        try {
            const response = await addReview(veterinarianId, reviewerId, reviewInfo);
            setSuccessMessage(response.message);
            if(onReviewSubmit) {
                onReviewSubmit();
            }
        } catch (error) {
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true);
        }
    }

    return (
        <React.Fragment>
            <Form>
                <h3>Rate this doctor:</h3>
                <div>
                    {[...Array(5)].map((_, index) => {
                        const ratingValue = index + 1;
                        return (
                            <Form.Label key ={index}>
                                <Form.Check
                                    type="radio"
                                    name="rating"
                                    value={ratingValue}
                                    onChange={handleInputChange}
                                    checked={reviewInfo.rating === ratingValue}
                                    inline
                                />
                            </Form.Label>
                        )
                    })}


                </div>
            </Form>
        </React.Fragment>
    )
}

export default Rating;