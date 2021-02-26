import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ReglementAchatService } from 'app/entities/reglement-achat/reglement-achat.service';
import { IReglementAchat, ReglementAchat } from 'app/shared/model/reglement-achat.model';
import { TypeReglement } from 'app/shared/model/enumerations/type-reglement.model';

describe('Service Tests', () => {
  describe('ReglementAchat Service', () => {
    let injector: TestBed;
    let service: ReglementAchatService;
    let httpMock: HttpTestingController;
    let elemDefault: IReglementAchat;
    let expectedResult: IReglementAchat | IReglementAchat[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ReglementAchatService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ReglementAchat(0, currentDate, TypeReglement.VIREMENT);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ReglementAchat', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReglement: currentDate,
          },
          returnedFromService
        );

        service.create(new ReglementAchat()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ReglementAchat', () => {
        const returnedFromService = Object.assign(
          {
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
            typeReglement: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReglement: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ReglementAchat', () => {
        const returnedFromService = Object.assign(
          {
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
            typeReglement: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReglement: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ReglementAchat', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
