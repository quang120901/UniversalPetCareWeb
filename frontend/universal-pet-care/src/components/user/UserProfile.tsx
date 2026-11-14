import React, { useState } from "react";
import { Card, Col, Row } from "react-bootstrap";
import UserImage from "../common/UserImage";
import { Link } from "react-router-dom";

const UserProfile = ({user}) => {
    const [showImageUploaderModal, setShowImageUploaderModal] = useState(false);
    const [showChangePasswordModal, setShowChangePasswordModal] = useState(false);

    const handleShowImageUploaderModal = () => {
        setShowImageUploaderModal(true);
    };
    const handleCloseImageUploaderModal = () => {
        setShowImageUploaderModal(false);
    };
    const handleShowChangePasswordModal = () => {
        setShowChangePasswordModal(true);
    };
    const handleCloseChangePasswordModal = () => {
        setShowChangePasswordModal(false);
    };

    return (
        <React.Fragment>
            <Row>
                <Col md={3}>
                    <Card>
                        <Card.Body>
                            <UserImage userId={user.id} userPhoto={user.photo} />
                        </Card.Body>
                        <div>
                            <Link to={"#"} onClick={handleShowImageUploaderModal}>Update Photo</Link>
                        </div>
                    </Card>
                </Col>
            </Row>
        </React.Fragment>
    )
}

export default UserProfile;