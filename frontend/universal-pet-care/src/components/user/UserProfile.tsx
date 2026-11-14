import React, { useState } from "react";
import { Card, Col, Row } from "react-bootstrap";
import UserImage from "../common/UserImage";
import { Link } from "react-router-dom";
import ImageUploaderModal from "../modals/ImageUploaderModal";
import ChangePasswordModal from "../modals/ChangePasswordModal";

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
                    <Card className="text-center mb-3 shadow">
                        <Card.Body>
                            <UserImage userId={user.id} userPhoto={user.photo} />
                        </Card.Body>
                        <div className="text-center">
                            <Link to={"#"} onClick={handleShowImageUploaderModal}>
                                Update Photo
                            </Link>
                            <ImageUploaderModal 
                                userId={user.id}
                                show={showImageUploaderModal}
                                handleClose={handleCloseImageUploaderModal} />

                            <Link to={"#"} onClick={""}>
                                Remove Photo
                            </Link>

                            <Link to={"#"} onClick={handleShowChangePasswordModal}>
                                Change Password
                            </Link>

                            <ChangePasswordModal 
                                userId={user.id}
                                show={showChangePasswordModal}
                                handleClose={handleCloseChangePasswordModal}
                            />
                        </div>
                    </Card>
                </Col>

                <Col md={8}>
                    <Card>
                        <Card.Body>
                            <Col md={4}>First Name</Col>
                            <Col md={4}>
                                <Card.Text>{user.firstName}</Card.Text>
                            </Col>
                        </Card.Body> 
                        <Card.Body>
                            <Col md={4}>Last Name</Col>
                            <Col md={4}>
                                <Card.Text>{user.lastName}</Card.Text>
                            </Col>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </React.Fragment>
    )
}

export default UserProfile;