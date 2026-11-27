import React from "react";
import { Accordion, Button, Col, Container, Row } from "react-bootstrap";
import ReactDatePicker from "react-datepicker";
import PetsTable from "../pet/PetsTable";

const UserAppointments = ({appointments}) => {
    return (
        <Container className="p-5">
            <Accordion className="mt-4 mb-5">
                {appointments.map((appointment, index) => {
                    return(<Accordion.Item eventKey={index} key={index} className="mb-2">
                        <Accordion.Header>
                            <div>
                                <div className="mb-3">Date: { appointment.appointmentDate }</div>
                                <div>Status: { appointment.status }</div>
                            </div>
                        </Accordion.Header>
                        <Accordion.Body>
                            <Row>
                                <Col md={4} className="m-t2">
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
                                    <PetsTable
                                        pets={appointment.pets}
                                        appointmentId={appointment.id}
                                        onPetsUpdate={}
                                    />
                                </Col>
                            </Row>

                            <div>
                                <Button variant="warning" size="sm">
                                    Update appointment
                                </Button>
                                <Button variant="danger" size="sm" className="ms-2">
                                    Cancel appointment
                                </Button>
                            </div>
                        </Accordion.Body>
                    </Accordion.Item>)
                })}
            </Accordion>
        </Container>
    )
}

export default UserAppointments;    