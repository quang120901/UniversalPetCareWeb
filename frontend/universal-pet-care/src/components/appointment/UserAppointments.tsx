import React from "react";
import { Accordion, Col, Container, Row } from "react-bootstrap";
import ReactDatePicker from "react-datepicker";

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

                                    <ReactDatePicker
                                        selected={
                                            new Date(
                                                `${appointment.appointmentDate}T${appointment.appointmentTime}`
                                            )
                                        }
                                        showTimeSelect
                                        timeFormat="HH:mm"
                                        timeIntervals={30}
                                        timeCaption="time"
                                        dateFormat="MMMM d, yyyy h:mm aa"
                                        inline
                                    />

                                    <p>
                                        Time :
                                        <span className="text-info">
                                            {" "}
                                            {appointment.appointmentTime}
                                        </span>{" "}
                                    </p>
                                    <p>Reason: {appointment.reason}</p>
                                </Col>

                                <Col md={8} className="mt-2">
                                    <h4>Pets:</h4>
                                    The pet table is coming here
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