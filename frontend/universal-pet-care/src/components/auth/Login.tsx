import React, { use } from "react";
import { Card, Col, Container, Form, InputGroup, Row } from "react-bootstrap";
import { BsLockFill, BsPersonFill } from "react-icons/bs";

const Login = () => {
    const [credentials, setCredentials] = use({
        email: "",
        password: "",
    })

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setCredentials((prevState) => ({
            ...prevState,
            [name]: value,
        }))
    }
    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col sm={6}>
                    <Card>
                        <Card.Body>
                            <Card.Title className="text-center mb-4"></Card.Title>
                            <Form>
                                <Form.Group className="mb-3" controlId="username">
                                    <Form.Label>Username</Form.Label>
                                    <InputGroup>
                                        <InputGroup.Text>
                                            <BsPersonFill /> {/* User icon */}
                                        </InputGroup.Text>
                                        <Form.Control
                                            type="text"
                                            name="email"
                                            value={credentials.email}
                                            onChange={handleChange}
                                        />
                                    </InputGroup>
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Label>Password</Form.Label>
                                    <InputGroup>
                                        <InputGroup.Text>
                                            <BsLockFill /> {/* Lock icon */}
                                        </InputGroup.Text>
                                        <Form.Control
                                            type="password"
                                            name="password"
                                            value={credentials.password}
                                            onChange={handleChange}
                                        />
                                    </InputGroup>
                                </Form.Group>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    )
}

export default Login;