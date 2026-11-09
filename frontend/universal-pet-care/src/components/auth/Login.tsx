import React, { use } from "react";
import { Card, Col, Container, Form, Row } from "react-bootstrap";

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
                                <Form.Group>
                                    <Form.Label>Username</Form.Label>
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