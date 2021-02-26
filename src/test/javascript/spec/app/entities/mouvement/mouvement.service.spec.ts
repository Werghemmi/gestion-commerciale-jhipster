import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MouvementService } from 'app/entities/mouvement/mouvement.service';
import { IMouvement, Mouvement } from 'app/shared/model/mouvement.model';
import { TypeMouvement } from 'app/shared/model/enumerations/type-mouvement.model';

describe('Service Tests', () => {
  describe('Mouvement Service', () => {
    let injector: TestBed;
    let service: MouvementService;
    let httpMock: HttpTestingController;
    let elemDefault: IMouvement;
    let expectedResult: IMouvement | IMouvement[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MouvementService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Mouvement(0, currentDate, TypeMouvement.ACHAT, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateMvt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Mouvement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateMvt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateMvt: currentDate,
          },
          returnedFromService
        );

        service.create(new Mouvement()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Mouvement', () => {
        const returnedFromService = Object.assign(
          {
            dateMvt: currentDate.format(DATE_TIME_FORMAT),
            typeMvt: 'BBBBBB',
            qteMvt: 1,
            ancienQte: 1,
            nvQte: 1,
            ancienPrix: 1,
            nvPrix: 1,
            prix: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateMvt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Mouvement', () => {
        const returnedFromService = Object.assign(
          {
            dateMvt: currentDate.format(DATE_TIME_FORMAT),
            typeMvt: 'BBBBBB',
            qteMvt: 1,
            ancienQte: 1,
            nvQte: 1,
            ancienPrix: 1,
            nvPrix: 1,
            prix: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateMvt: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Mouvement', () => {
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
