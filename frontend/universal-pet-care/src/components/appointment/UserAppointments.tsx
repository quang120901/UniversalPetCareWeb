import React from "react";
import { Accordion, Col, Container, Row } from "react-bootstrap";

const UserAppointments = ({appointments}) => {
    return (
        <Container>
            <Accordion>
                {appointments.map((appointment, index) => {
                    return(<Accordion.Item eventKey={index} key={index}>
                        <Accordion.Header>
                            <div>
                                <div>Date: { appointment.appointmentDate }</div>
                                <div>Status: { appointment.status }</div>
                            </div>
                        </Accordion.Header>
                        <Accordion.Body>
                            <Row>
                                <Col>
                                    <p>
                                        Appointment Number :{" "}
                                        <span className="text-info">
                                            {appointment.appointmentNo}
                                        </span>{" "}
                                    </p>
                                </Col>
                            </Row>
                        </Accordion.Body>
                    </Accordion.Item>)
                })}
            </Accordion>
        </Container>
    )
}

export default UserAppointments;    